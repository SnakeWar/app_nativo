package app.mayrcon.com.br.navigationview.View;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import app.mayrcon.com.br.navigationview.Controller.CRUD;
import app.mayrcon.com.br.navigationview.Model.ObjectItem;
import app.mayrcon.com.br.navigationview.R;
import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsereFragment extends Fragment {


    public InsereFragment() {
        // Required empty public constructor
    }

    Button addBtn, backBtn;
    EditText addTitulo, addDesc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_insere, container, false);
        backBtn = view.findViewById(R.id.backBtn);
        addBtn = view.findViewById(R.id.addBtn);
        addTitulo = view.findViewById(R.id.add_titulo);
        addDesc = view.findViewById(R.id.add_desc);
        addTitulo.requestFocus();

        addBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectItem objectItem = new ObjectItem();
                objectItem.setTitle(addTitulo.getText().toString());
                objectItem.setDesc(addDesc.getText().toString());
                CRUD u = new CRUD(getActivity().getApplicationContext());
                if (u.insertObject(objectItem)) {
                    addTitulo.setEnabled(false);
                    addDesc.setEnabled(false);
                    backBtn.setEnabled(false);
                    addBtn.setEnabled(false);
                    Toast.makeText(getActivity(), "Inserido com Sucesso!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                    ListaFragment listaFragment = new ListaFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(
                            R.id.relativelayout_insere,
                            listaFragment,
                            listaFragment.
                                    getTag()).
                            commit();
                        }
                    }, 1000);
                } else {
                    Toast.makeText(getActivity(), "Erro ao Inserir", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            ListaFragment listaFragment = new ListaFragment();
                            FragmentManager manager = getActivity().getSupportFragmentManager();
                            manager.beginTransaction().replace(
                                    R.id.relativelayout_insere,
                                    listaFragment,
                                    listaFragment.
                                            getTag()).
                                    commit();
            }
        });
        return view;
    }

}
