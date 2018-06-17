package app.mayrcon.com.br.navigationview.View;


import android.os.Bundle;
import android.os.Handler;
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
public class EditFragment extends Fragment {


    public EditFragment() {
        // Required empty public constructor
    }

    Button editBtn, backBtn;
    EditText editTitulo, editDesc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        backBtn = view.findViewById(R.id.backBtn);
        editBtn = view.findViewById(R.id.addBtn);
        editTitulo = view.findViewById(R.id.add_titulo);
        editDesc = view.findViewById(R.id.add_desc);
        editTitulo.requestFocus();

        editBtn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View view) {
                int id=0;
                CRUD u = new CRUD(getActivity().getApplicationContext());
                String titulo = Paper.book().read("editar");
                System.out.println("Título do Editar: " + titulo);
                ArrayList<ObjectItem> object2 = u.getObject();
                int numero = object2.size();
                for (int i=0; i<object2.size();i++){
                    if(object2.get(i).getTitle().equals(titulo)){
                        id = object2.get(i).getId();
                    }
                }
                ObjectItem objectItem = new ObjectItem();
                System.out.println("ID do Editar: " + id + "Tamanho da Array: " + numero);
                objectItem.setId(id);
                objectItem.setTitle(editTitulo.getText().toString());
                objectItem.setDesc(editDesc.getText().toString());
                if (u.updateObject(objectItem)) {
                    editTitulo.setEnabled(false);
                    editDesc.setEnabled(false);
                    backBtn.setEnabled(false);
                    editBtn.setEnabled(false);
                    Toast.makeText(getActivity(), "Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                    ListaFragment listaFragment = new ListaFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(
                            R.id.relativelayout_editar,
                            listaFragment,
                            listaFragment.
                                    getTag()).
                            commit();
                        }
                    }, 1000);
                } else {
                    Toast.makeText(getActivity(), "Erro ao Atualizar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaFragment listaFragment = new ListaFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(
                        R.id.relativelayout_editar,
                        listaFragment,
                        listaFragment.
                                getTag()).
                        commit();
            }
        });
        return view;
    }

}
