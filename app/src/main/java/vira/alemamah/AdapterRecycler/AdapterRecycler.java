package vira.alemamah.AdapterRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vira.alemamah.ModelSubjectBehavior.ModelSubjectBehavior;
import seens.alemamah.R;
import vira.alemamah.ViewHolder.ViewHolderBehavior;

public class AdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ModelSubjectBehavior> subject;
    ViewHolderBehavior viewHolderBehavior;

    public AdapterRecycler(Context context, ArrayList<ModelSubjectBehavior> subject) {
        this.context = context;
        this.subject = subject;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_behavior,null);
        return new ViewHolderBehavior(view,context,"main");
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        viewHolderBehavior= (ViewHolderBehavior) holder;
        if (subject.get(position).isSubTitle()){
            viewHolderBehavior.textView.setText(subject.get(position).getCategory());
        }else {
            viewHolderBehavior.imageView.setVisibility(View.INVISIBLE);
            viewHolderBehavior.textView.setText(subject.get(position).getCategory());
        }
    }
    @Override
    public int getItemCount() {
        return subject.size();
    }
}
