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

public class RetrieveArduinoID
{

    @SuppressLint("StaticFieldLeak")
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static DocumentReference docRef = db.collection("ArduinoIDs").document("inUse");

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    private static ArrayList<String> usedID = new ArrayList<>();


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

//        if (ifIDExists("uivwA"))
//        {
//            updateIDs(ID);
//            return ID;
//        }
//        else
//        {
//            generateRandomString(5);
//        }
//
//        return ID;
        updateIDs(ID);
        return ID;

    }

//    private static boolean ifIDExists(final String ID)
//    {
//
//        final Boolean[] condition = {false};
//
//        docRef.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
//                {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot)
//                    {
//                        if (documentSnapshot.exists())
//                        {
//                            Map<String, Object> checker = new HashMap<>();
//
//                            checker = (Map<String, Object>) documentSnapshot.get("usedID");
//
//                            if (checker.containsValue(ID))
//                            {
//                                condition[0] = true;
//                            }
//                            else
//                            {
//                                condition[0] = false;
//                            }
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener()
//                {
//                    @Override
//                    public void onFailure(@NonNull Exception e)
//                    {
//                        //Do nothing for now
//                    }
//                });
//
//        Log.d(TAG, "condition is " + condition[0]);
//        return condition[0];
//
//
//
//    }


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
        //Log.d(TAG, "condition is" + condition[0]);
        //return condition[0];
        
    }
}
