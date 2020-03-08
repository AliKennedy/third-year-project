package ca326.petwatch.petwatch;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;


// A class to generate a random Arduino I.D.
// This class was made when the idea to link app and Arduino was through bluetooth
public class RetrieveArduinoID
{
    // Firebase Database variables to enter the generated I.D. into the database
    @SuppressLint("StaticFieldLeak")
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static DocumentReference docRef = db.collection("ArduinoIDs").document("inUse");

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    private static ArrayList<String> usedID = new ArrayList<>();


    // Method to generate a random string
    public static String generateRandomString(int length)
    {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
        {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);



            sb.append(rndChar);

        }

        String ID = sb.toString();


        updateIDs(ID);
        return ID;

    }

    // A method to update the I.D. in the database and too check if its already in the database
    private static void updateIDs(final String ID)
    {


        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        if (documentSnapshot.exists())
                        {
                            Map<String, Object> data = new HashMap<>();

                            data = (Map<String, Object>) documentSnapshot.get("usedID");

                            if (data.containsValue(ID))
                            {
                                generateRandomString(5);

                            }
                            else
                            {
                                data.put(ID, ID);
                                docRef.set(data)
                                        .addOnSuccessListener(new OnSuccessListener<Void>()
                                        {
                                            @Override
                                            public void onSuccess(Void aVoid)
                                            {
                                                //Do nothing for now

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener()
                                        {
                                            @Override
                                            public void onFailure(@NonNull Exception e)
                                            {
                                                //Do nothing for now

                                            }
                                        });
                            }
                        }
                        else
                        {
                            Log.d(TAG, "document does not exist");
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.d(TAG, "nothing works");
                    }
                });
    }
}
