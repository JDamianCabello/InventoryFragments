package es.jdamiancabello.inventory.ui.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class BaseDialogFragment extends DialogFragment {


    public static final String TITTLE = "tittle";
    public static final String MESSAGE = "message";
    public static final String TAG = "BaseDialogFragment";


    public static Fragment newInstance(Bundle bundle){
        BaseDialogFragment dialogFragment = new BaseDialogFragment();
        if(bundle != null)
            dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    //Método callback del listener de DialogFragment
    //Cuando se pulsa ACEPTAR
    public interface OnAcceptDialogListener{
        void onAcceptDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        String tittle = getArguments().getString(TITTLE);
        String message = getArguments().getString(MESSAGE);

        //Se crea el cuadro de diálogo usando el builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(tittle);
        builder.setMessage(message);
        builder.setPositiveButton(getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                OnAcceptDialogListener listener = (OnAcceptDialogListener) getTargetFragment();
                listener.onAcceptDialog();
            }
        });
        builder.setNegativeButton(android.R.string.no,null);
        return builder.create();
    }
}
