package com.example.gestionnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declaration des variables
    ListView listeView;
    private EditText Note;
    static double somme;
    private Button button;
    private TextView moyenne;
    private ArrayList<String> itemsN;
    static ArrayList<String> ModuleExiste;
    ArrayAdapter<String> adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Spinner dropdown = findViewById(R.id.spinner);
        // la liste des modules
        String[] modules = new String[]{"Analysis, Mining and Indexing in Big Multimedia Systems", "Développement Mobile", "Big Data Analytics", "IOT", "Big Data et Sécurité", "Management et Gestion de Projet"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, modules);
        dropdown.setAdapter(adapter);
        //pour initialiser des variables
        listeView = findViewById(R.id.listViewModules);
        Note = findViewById(R.id.Note);
        button = findViewById(R.id.Ajouter);
        moyenne = findViewById(R.id.Moyenne);
        ModuleExiste = new ArrayList<>();
        itemsN = new ArrayList<>();
        adap = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, itemsN);
        listeView.setAdapter(adap);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                String n = Note.getText().toString();
                String module = dropdown.getSelectedItem().toString();
                //il est interdit d'ajouter un module sans taper sa note
                if (n == null || n.length() == 0) {
                    makeToast("Il est obligatoir d'entrer la note !");
                } else {
                    //si le module existe dans listView
                    if (existe(ModuleExiste, module)) {
                        makeToast("la note du module: " + module + " existe déjà");
                    } else {
                        //Ajouter le nom et la note du module àlitView
                        addItem(n, module);
                        double no = Double.parseDouble(n);
                        //calcul de la moyenne
                        somme = somme + no;
                        double moy = somme / (itemsN.size());
                        moyenne.setText("La moyenne : " + moy);
                        Note.setText("");
                    }


                }

            }
        });
    }

    //afficher les méssages d'erreur
    Toast t;
    public void makeToast(String s){
        if(t!=null) t.cancel();
        t=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        t.show();
    }
    //ajouter le nom et la note du module à ListView
    public void addItem( String item,String module){
        int n=itemsN.size()+1;
        ModuleExiste.add(module);
        itemsN.add(n+"          "+item+"         "+module);
        listeView.setAdapter(adap);
    }
    //Vérifier si la note d'un module  existe déjà
    public boolean existe(ArrayList<String> ModuleExiste, String val){
        boolean ex=false;
        for(int i = 0 ; i<ModuleExiste.size();i++){
            if(val.equals(ModuleExiste.get(i))){
                ex=true;
            }
        }
        return ex;
    }
}