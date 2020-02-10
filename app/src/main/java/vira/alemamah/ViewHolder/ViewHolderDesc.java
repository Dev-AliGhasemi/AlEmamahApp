package vira.alemamah.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class ViewHolderDesc extends RecyclerView.ViewHolder {
    public TextView textViewNameEmam,textViewDateBorn,textViewDateDeath,textViewDateBornEdition,textViewDateDeathEdition,textViewFatherName,textViewMotherName,textViewFatherNameEdition,textViewMotherNameEdtion,textViewAdjective,textViewAdjectiveEdition;
    public ImageView imageViewFavorite,imageViewShare;
    public CircleImageView circleImageView;
    public Button buttonRead;
    private SetFonts setFontsClass;
    private Context context;
    public ViewHolderDesc(View itemView, final Context context) {
        super(itemView);
        this.context = context;
        initializing();
        imageViewShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,buttonRead.getTag().toString());
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent,"اشتراک گذاری متن."));
            }
            });
    }

    private void initializing() {
        textViewNameEmam = (TextView) itemView.findViewById(R.id.textViewNameEmam);
        textViewDateBorn = (TextView) itemView.findViewById(R.id.textViewDateBorn);
        textViewDateDeath = (TextView) itemView.findViewById(R.id.textViewDateِDeath);
        textViewDateBornEdition = (TextView) itemView.findViewById(R.id.textViewDateِBornEdition);
        textViewDateDeathEdition = (TextView) itemView.findViewById(R.id.textViewDateِDeathEdition);
        textViewFatherName = (TextView) itemView.findViewById(R.id.textViewFatherNameDesc);
        textViewMotherName = (TextView) itemView.findViewById(R.id.textViewMohterNameDesc);
        textViewFatherNameEdition = (TextView) itemView.findViewById(R.id.textViewFatherNameDescEdition);
        textViewMotherNameEdtion = (TextView) itemView.findViewById(R.id.textViewMotherNameDescEdition);
        textViewAdjective = (TextView) itemView.findViewById(R.id.textViewAdjectiveDesc);
        textViewAdjectiveEdition = (TextView) itemView.findViewById(R.id.textViewAdjectiveDescEdition);
        imageViewFavorite = (ImageView) itemView.findViewById(R.id.imageFavoriteDesc);
        imageViewShare = (ImageView) itemView.findViewById(R.id.imageShareDesc);
        circleImageView = (CircleImageView) itemView.findViewById(R.id.imageCircleDesc);
        buttonRead = (Button) itemView.findViewById(R.id.btnReadLive);
        setFonts();
    }

    private void setFonts() {
        setFontsClass = new SetFonts(context,context.getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",textViewNameEmam);
        setFontsClass.setFont("TextView",textViewDateBorn);
        setFontsClass.setFont("TextView",textViewDateDeath);
        setFontsClass.setFont("TextView",textViewDateBornEdition);
        setFontsClass.setFont("TextView",textViewDateDeathEdition);
        setFontsClass.setFont("TextView",textViewFatherName);
        setFontsClass.setFont("TextView",textViewMotherName);
        setFontsClass.setFont("TextView",textViewFatherNameEdition);
        setFontsClass.setFont("TextView",textViewMotherNameEdtion);
        setFontsClass.setFont("TextView",textViewAdjective);
        setFontsClass.setFont("TextView",textViewAdjectiveEdition);
        setFontsClass.setFont("Button",buttonRead);
    }

}
