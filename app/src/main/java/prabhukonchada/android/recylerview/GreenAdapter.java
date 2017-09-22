/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package prabhukonchada.android.recylerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {


    private static final String TAG = GreenAdapter.class.getSimpleName();

    private int mNumberItems;
    int count;
    ListItemListener itemListener;

    public GreenAdapter(int numberOfItems,ListItemListener itemListener) {
        mNumberItems = numberOfItems;
        count = 0;
        this.itemListener = itemListener;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        String indexNumberView = "View Holder Index Number : ";
        Log.d("onCreate","OnCreateViewHolder");
        count++;
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.viewHolderIndexView.setText(indexNumberView.concat(String.valueOf(count)));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mNumberItems;
    }


    class NumberViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView listItemNumberView;
        TextView viewHolderIndexView;

        public NumberViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndexView = (TextView) itemView.findViewById(R.id.index_number);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {

            listItemNumberView.setText(String.valueOf(listIndex));
        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            itemListener.onListItemClicked(clickedPosition);
        }
    }

    public interface ListItemListener{
        void onListItemClicked(int listItemPosition);
    }
}
