package com.androidkt.recyclerviewselection;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.androidkt.recyclerviewselection.adapter.MyAdapter;
import com.androidkt.recyclerviewselection.adapter.MyItemKeyProvider;
import com.androidkt.recyclerviewselection.adapter.MyItemLookup;
import com.androidkt.recyclerviewselection.model.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.OnDragInitiatedListener;
import androidx.recyclerview.selection.OnItemActivatedListener;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private MenuItem selectedItemCount;
    private SelectionTracker<Long> selectionTracker;
    private RecyclerView mRv;

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRv = findViewById(R.id.rv);
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        List<Item> randomList = getRandomList();
        mAdapter = new MyAdapter(randomList);
        mRv.setAdapter(mAdapter);

        selectionTracker = new SelectionTracker.Builder<>(
                "my-selection-id",
                mRv,
                new MyItemKeyProvider(1, randomList),
                new MyItemLookup(mRv),
                StorageStrategy.createLongStorage()
        )
                .withSelectionPredicate(new SelectionTracker.SelectionPredicate<Long>() {
                    @Override
                    public boolean canSetStateForKey(@NonNull Long key, boolean nextState) {
                        return true;
                    }

                    @Override
                    public boolean canSetStateAtPosition(int position, boolean nextState) {
                        return true;
                    }

                    @Override
                    public boolean canSelectMultiple() {
                        return false;
                    }
                })
                .withOnItemActivatedListener(new OnItemActivatedListener<Long>() {
                    @Override
                    public boolean onItemActivated(@NonNull ItemDetailsLookup.ItemDetails<Long> item, @NonNull MotionEvent e) {
                        Log.d(TAG, "Selected ItemId: " + item.getSelectionKey());
                        //如果不添加此处的选择，默认需要长按激活
                        selectionTracker.select(Objects.requireNonNull(item.getSelectionKey()));
                        return true;
                    }
                })
                .withOnDragInitiatedListener(new OnDragInitiatedListener() {
                    @Override
                    public boolean onDragInitiated(@NonNull MotionEvent e) {
                        Log.d(TAG, "onDragInitiated");
                        return true;
                    }

                })
                .build();
        mAdapter.setSelectionTracker(selectionTracker);
        selectionTracker.addObserver(new SelectionTracker.SelectionObserver<Long>() {
            @Override
            public void onItemStateChanged(@NonNull Long key, boolean selected) {
                super.onItemStateChanged(key, selected);
            }

            @Override
            public void onSelectionRefresh() {
                super.onSelectionRefresh();
            }

            @Override
            public void onSelectionChanged() {
                super.onSelectionChanged();
                setMenuItemTitle(selectionTracker.getSelection().size());

                Iterator<Long> itemIterable = selectionTracker.getSelection().iterator();
                while (itemIterable.hasNext()) {
                    Log.i(TAG, itemIterable.next() + "");
                }
            }

            @Override
            public void onSelectionRestored() {
                super.onSelectionRestored();
            }
        });

        if (savedInstanceState != null) {
            selectionTracker.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        selectionTracker.onSaveInstanceState(outState);
    }

    public void setMenuItemTitle(int selectedItemSize) {
        selectedItemCount.setTitle("" + selectedItemSize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        selectedItemCount = menu.findItem(R.id.action_item_count);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                // TODO: 19-3-12
                break;
            case R.id.action_clear:
                selectionTracker.clearSelection();
                break;
        }
        return true;
    }

    private List<Item> getRandomList() {
        Random random = new Random();
        List<Item> items = new ArrayList<>();
        Item item;
        for (int i = 1; i <= 100; i++) {
            item = new Item();
            item.setItemId(i);
            item.setItemName("Item Name " + i);
            item.setItemPrice(random.nextFloat() * 1000);
            items.add(item);
        }

        return items;
    }
}
