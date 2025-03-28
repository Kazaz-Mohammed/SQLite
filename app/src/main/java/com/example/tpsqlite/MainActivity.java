package com.example.tpsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpsqlite.classes.Etudiant;
import com.example.tpsqlite.service.EtudiantService;

import java.util.Calendar;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        EtudiantService es = new EtudiantService(this);
//
//        //Insertion des étudiants
//        es.create(new Etudiant("ALAMI", "ALI"));
//        es.create(new Etudiant("RAMI", "AMAL"));
//        es.create(new Etudiant("SAFI", "MHMED"));
//        es.create(new Etudiant("SELAOUI", "REDA"));
//        es.create(new Etudiant("ALAMI", "WAFA"));
//
//        //Parcourir la liste des étudiants
//        for(Etudiant e : es.findAll()){
//            Log.d(e.getId()+"", e.getNom()+" "+e.getPrenom());
//        }
//
//        //Supprimer l'élement dont id = 3
//        es.delete(es.findById(3));
//
//        //Liste après suppression
//        Log.d("delete","Après suppression");
//        for(Etudiant e : es.findAll()){
//            Log.d(e.getId()+"", e.getNom()+" "+e.getPrenom());
//        }
//
//    }

    private EtudiantService es;

    private EditText nom;
    private EditText prenom;
    private EditText dateNaissance;
    private Button add;


    private EditText id;
    private Button rechercher;
    private TextView res;

    private Button lister;
    private Button supprimer;
    //Méthode pour vider les champs après l’ajout
    void clear(){
        nom.setText("");
        prenom.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        es = new EtudiantService(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EtudiantService es = new EtudiantService(this);



        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        dateNaissance = findViewById(R.id.dateNaissance);
        add = findViewById(R.id.bn);
        id = findViewById(R.id.id);
        rechercher = findViewById(R.id.load);
        supprimer = findViewById(R.id.delete);
        lister = findViewById(R.id.list);
        res = findViewById(R.id.res);


        dateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nom.getText().toString().isEmpty() || prenom.getText().toString().isEmpty() || dateNaissance.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }
                es.create(new Etudiant(nom.getText().toString(), prenom.getText().toString(), dateNaissance.getText().toString()));
                clear();
                Toast.makeText(MainActivity.this, "Étudiant ajouté", Toast.LENGTH_SHORT).show();
            }
        });




        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = id.getText().toString();
                if (!idStr.isEmpty()) {
                    int etudiantId = Integer.parseInt(idStr);
                    Etudiant e = es.findById(etudiantId);
                    if (e != null) {
                        res.setText("ID: " + e.getId() + "\nNom: " + e.getNom() + "\nPrénom: " + e.getPrenom());
                    } else {
                        res.setText("Aucun étudiant trouvé avec cet ID");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez entrer un ID", Toast.LENGTH_SHORT).show();
                }
            }
        });


        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = id.getText().toString();
                if (!idStr.isEmpty()) {
                    int etudiantId = Integer.parseInt(idStr);
                    Etudiant e = es.findById(etudiantId);
                    if (e != null) {
                        es.delete(e);
                        res.setText("Étudiant supprimé");
                        Toast.makeText(MainActivity.this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Aucun étudiant trouvé avec cet ID", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez entrer un ID", Toast.LENGTH_SHORT).show();
                }
            }
        });


//
//        TextView textViewSelectedDate = findViewById(R.id.textViewDate);
//        Button btnDatePicker = findViewById(R.id.btnDatePicker);
//
//        btnDatePicker.setOnClickListener(v -> {
//            Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(
//                    MainActivity.this,
//                    (view, year1, month1, dayOfMonth) -> {
//                        String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
//                        textViewSelectedDate.setText(selectedDate);
//                    },
//                    year, month, day
//            );
//            datePickerDialog.show();
//        });
//

        EditText dateNaissance = findViewById(R.id.dateNaissance);

        dateNaissance.setOnClickListener(v -> {
            // Get the current date
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Open DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Set selected date in EditText
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateNaissance.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });



        imageView = findViewById(R.id.imageView);
        Button btnUploadImage = findViewById(R.id.btnUploadImage);

        btnUploadImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Sélectionner une image"), PICK_IMAGE_REQUEST);
        });


//        lister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Etudiant> etudiants = es.findAll();
//                if (etudiants.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Aucun étudiant trouvé", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                StringBuilder result = new StringBuilder();
//                for (Etudiant e : etudiants) {
//                    result.append("ID: ").append(e.getId())
//                            .append("\nNom: ").append(e.getNom())
//                            .append("\nPrénom: ").append(e.getPrenom())
//                            .append("\n-------------------\n");
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Liste des étudiants");
//                builder.setMessage(result.toString());
//
//
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        lister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Etudiant> etudiants = es.findAll();
                if (etudiants.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Aucun étudiant trouvé", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert list of students to a String array for the ListView
                final String[] etudiantArray = new String[etudiants.size()];
                for (int i = 0; i < etudiants.size(); i++) {
                    etudiantArray[i] = "ID: " + etudiants.get(i).getId() +
                            "\nNom: " + etudiants.get(i).getNom() +
                            "\nPrénom: " + etudiants.get(i).getPrenom();
                }

                // Create an AlertDialog with a ListView
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Liste des étudiants");
                builder.setItems(etudiantArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showOptionsDialog(etudiants.get(which));
                    }
                });

                builder.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
    // Function to show DatePickerDialog
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateNaissance.setText(selectedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void showOptionsDialog(final Etudiant etudiant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Options");

        String[] options = {"Modifier", "Supprimer"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showModifyDialog(etudiant);  // Call modify function
                } else if (which == 1) {
                    deleteEtudiant(etudiant);  // Call delete function
                }
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteEtudiant(final Etudiant etudiant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirmer la suppression");
        builder.setMessage("Voulez-vous vraiment supprimer cet étudiant ?");

        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                es.delete(etudiant); // Delete student from database
                Toast.makeText(MainActivity.this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showModifyDialog(final Etudiant etudiant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Modifier l'étudiant");

        // Create a layout with EditText fields
        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 20);

        final EditText inputNom = new EditText(MainActivity.this);
        inputNom.setHint("Nom");
        inputNom.setText(etudiant.getNom());

        final EditText inputPrenom = new EditText(MainActivity.this);
        inputPrenom.setHint("Prénom");
        inputPrenom.setText(etudiant.getPrenom());

        layout.addView(inputNom);
        layout.addView(inputPrenom);

        builder.setView(layout);

        builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etudiant.setNom(inputNom.getText().toString());
                etudiant.setPrenom(inputPrenom.getText().toString());

                es.update(etudiant);
                Toast.makeText(MainActivity.this, "Étudiant modifié", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }





}