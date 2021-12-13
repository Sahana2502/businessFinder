package com.rbc.yelp.services.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rbc.yelp.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.rbc.yelp.services.models.Business;
import com.rbc.yelp.services.models.Category;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {
    private Context context;
    private List<Business> businessList;
    public BusinessAdapter(Context context, List<Business> businessList) {
        this.context = context;
        this.businessList = businessList;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BusinessViewHolder(LayoutInflater.from(context).inflate(R.layout.businesses_row_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {

        holder.businessName.setText(businessList.get(position).getName());
        holder.setClickListener(this::displayBusinessDetails);
    }

    private void displayBusinessDetails(View view, int selectedPosition) {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.business_details_dialog, viewGroup, false);
        builder.setView(dialogView);


        //Initialize views
        TextView businessNameTV = dialogView.findViewById(R.id.business_details_name);
        RatingBar businessRating = dialogView.findViewById(R.id.rating_bar);
        TextView businessCategory = dialogView.findViewById(R.id.business_category);
        ImageView businessImage = dialogView.findViewById(R.id.business_image);
        ImageView buttonClose = dialogView.findViewById(R.id.button_close);

        //Set data to the view
        businessNameTV.setText(businessList.get(selectedPosition).getName());
        businessRating.setRating(businessList.get(selectedPosition).getRating());
        for (Category category : businessList.get(selectedPosition).getCategories())
            businessCategory.append("\u2022" + category.getTitle() + "\n");

        //Load business image
        Glide.with(context).load(businessList.get(selectedPosition).getImage_url()).centerCrop().into(businessImage);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        buttonClose.setOnClickListener(view1 -> alertDialog.dismiss());
    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public static final class BusinessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView businessName;
        private ItemClickListener clickListener;

        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);

            businessName = itemView.findViewById(R.id.business_name);
            itemView.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.clickListener.onClick(v, getAdapterPosition());
        }
    }
}
