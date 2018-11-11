package weechan.com.loader;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import weechan.com.loading.Loading;
import weechan.com.loading.LoadingBuilder;

public class MainActivity extends AppCompatActivity {
    int state = 0;
    int state2 = 0;

    public static class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<Holder>() {

            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item, null, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull Holder viewHolder, int i) {
                viewHolder.textView.setText(i + " item ");
            }

            @Override
            public int getItemCount() {
                return 90;
            }
        });


        final Loading loading = new LoadingBuilder(this).build(recyclerView);
        final Loading loading1 = new LoadingBuilder(this).build(findViewById(R.id.imageView));
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0){
                    loading.load();
                }

                if( state == 1) {
                    loading.error();
                }

                if( state == 2){
                    loading.retry();
                }

                if ( state == 3){
                    loading.finish();
                }

                state = (state + 1 ) % 4;
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state2 == 0){
                    loading1.load();
                }

                if( state2 == 1) {
                    loading1.error();
                }

                if( state2 == 2){
                    loading1.retry();
                }

                if ( state2 == 3){
                    loading1.finish();
                }

                state2 = (state2 + 1 ) % 4;
            }
        });
    }
}
