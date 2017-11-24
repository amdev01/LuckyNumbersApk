package com.luckynumbers.mycax.luckynumbers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.Myholder> {
    List<DataModel> dataModelArrayList;
    Context context;

    public DatabaseAdapter(List<DataModel> dataModelArrayList, Context context) {
        this.dataModelArrayList = dataModelArrayList;
        this.context = context;
    }

    class Myholder extends RecyclerView.ViewHolder implements CardView.OnLongClickListener {
        TextView name, result;

        public Myholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.card_name);
            result = (TextView) itemView.findViewById(R.id.card_result);
            CardView cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            /*new AlertDialog.Builder(context)
                    .setIcon(R.drawable.ic_warning_black_24dp)
                    .setTitle("Delete result")
                    .setMessage("Are you sure you want delete this result?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //DataBHelper database = new DataBHelper(context);
                            //ResultsFragment resultsFragment = new ResultsFragment();
                            //database.deleteEntry(getAdapterPosition());
                            //notifyItemRemoved(getAdapterPosition());
                            //notifyDataSetChanged();
                            //Log.e("DATABASE/E", "Could not remove item from the list");
                            //resultsFragment.datamodel.remove(getAdapterPosition());
                            //database.close();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();*/
            return true;
        }
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        DataModel dataModel = dataModelArrayList.get(position);
        holder.name.setText(dataModel.getName());
        holder.result.setText(dataModel.getResult());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}



