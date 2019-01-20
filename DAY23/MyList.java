public class MainActivity extends AppCompatActivity {
    ListView listView;
    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("학생1","010-9794-4571", R.drawable.ic_launcher_foreground));
        adapter.addItem(new SingerItem("학생2","010-9794-4572", R.drawable.ic_launcher_foreground));
        adapter.addItem(new SingerItem("학생3","010-9794-4573", R.drawable.ic_launcher_background));
        adapter.addItem(new SingerItem("학생4","010-9794-4574", R.drawable.ic_launcher_foreground));
        adapter.addItem(new SingerItem("학생5","010-9794-4575", R.drawable.ic_launcher_background));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 : " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.addItem(new SingerItem(name,mobile,R.drawable.ic_launcher_background));
                adapter.notifyDataSetChanged();
            }
        });
    }


    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        <--! 뷰를 재사용 하는 부분 -->
            SingerItemView view = null;
            if(convertView == null){
                view = new SingerItemView(getApplicationContext());
            } else{
                view = (SingerItemView) convertView;
            }


            SingerItem item = items.get(position);

            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;
        }
    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class SingerItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    ImageView imageview;

    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        imageview = (ImageView) findViewById(R.id.imageview);


    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }

    public void setImage(int resId) {
        imageview.setImageResource(resId);
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class SingerItem {

    String name;
    String mobile;
    int resId;

    public SingerItem(String name, String mobile, int resId) {
        this.name = name;
        this.mobile = mobile;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
