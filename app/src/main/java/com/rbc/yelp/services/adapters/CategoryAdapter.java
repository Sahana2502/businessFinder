package com.rbc.yelp.services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbc.yelp.R;
import com.rbc.yelp.services.models.Business;
import com.rbc.yelp.services.models.CategorizedBusiness;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MainViewHolder> {

    private Context context;
    private List<CategorizedBusiness> categoryBusinessList;

    public CategoryAdapter(Context context, List<CategorizedBusiness> categoryBusinessList) {
        this.context = context;
        this.categoryBusinessList = categoryBusinessList;
    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MainViewHolder viewHolder, int i) {
        viewHolder.categoryTitle.setText(categoryBusinessList.get(i).getCategoryName()+"("+categoryBusinessList.get(i).getBusinessList().size()+")");
        setCategoryItemRecycler(viewHolder.itemRecycler, categoryBusinessList.get(i).getBusinessList());

    }

    @Override
    public int getItemCount() {
        return categoryBusinessList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;
        RecyclerView itemRecycler;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
        }
    }

    private void setCategoryItemRecycler(RecyclerView recyclerView, List<Business> businessList){

        BusinessAdapter businessAdapter = new BusinessAdapter(context, businessList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(businessAdapter);

    }
}