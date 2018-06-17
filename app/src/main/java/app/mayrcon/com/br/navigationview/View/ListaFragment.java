package app.mayrcon.com.br.navigationview.View;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.mayrcon.com.br.navigationview.BroadCastReceiver.MyApplication;
import app.mayrcon.com.br.navigationview.Controller.CRUD;
import app.mayrcon.com.br.navigationview.Model.ObjectItem;
import app.mayrcon.com.br.navigationview.R;
import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    ListView simpleListView;
    FloatingActionButton plusBtn;

    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        plusBtn = view.findViewById(R.id.floatingActionButton);
        simpleListView = view.findViewById(R.id.listview);
        carregarDados();
        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                final CRUD d = new CRUD(getActivity().getApplicationContext());
                final ArrayList<ObjectItem> object = d.getObject();
                adb.setTitle("Título: " + object.get(position).getTitle().toUpperCase());
                adb.setMessage("Descrição: " + object.get(position).getDesc());
                final int positionToRemove = position;
                adb.setNegativeButton("Editar", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Paper.book().write("editar", object.get(positionToRemove).getTitle());
                        EditFragment editFragment = new EditFragment();
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        manager.beginTransaction().replace(
                                R.id.relativelayout_for_lista,
                                editFragment,
                                editFragment.
                                        getTag()).
                                commit();
                    }});
                adb.setPositiveButton("Excluir", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ObjectItem objectItem = new ObjectItem();
                        objectItem.setId(object.get(positionToRemove).getId());
                        objectItem.setTitle(object.get(positionToRemove).getTitle());
                        objectItem.setDesc(object.get(positionToRemove).getDesc());
                        if (d.deleteObject(objectItem)) {
                            carregarDados();
                            Toast.makeText(getActivity(), "Excluído com Sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Erro ao Excluir", Toast.LENGTH_SHORT).show();
                        }

                    }});
                adb.show();
            }
        });
        plusBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsereFragment insereFragment = new InsereFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(
                        R.id.relativelayout_for_lista,
                        insereFragment,
                        insereFragment.
                                getTag()).
                        commit();
            }
        });
        return view;
    }
    public void carregarDados() {
        CRUD b = new CRUD(getActivity().getApplicationContext());
        ArrayList<ObjectItem> object = b.getObject();
        List<String> campos = new ArrayList<String>();
        for (int i = 0; i < object.size(); i++) {
            campos.add(object.get(i).getTitle().toUpperCase());
            Log.d(object.get(i).getTitle(), "Título: " + object.get(i).getTitle());
            Log.d(object.get(i).getDesc(), "Desc: " + object.get(i).getDesc());
        }
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row, R.id.textView4, campos);
        simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

    }
}
