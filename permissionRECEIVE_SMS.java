@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS); //권한이 허용되어 있나?
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"SMS 수신 권한 있음.", Toast.LENGTH_LONG).show();
        } else if(permissionCheck == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"SMS 수신 권한 없음,", Toast.LENGTH_LONG).show();
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
                Toast.makeText(this,"SMS 권한 설명 필요함", Toast.LENGTH_LONG).show();
            } else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS},101);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 101:
                if(grantResults.length > 0){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED) //패키지 매니저에서 관한 수락.
                        Toast.makeText(this,"SMS 수신 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show();
                } else if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this,"SMS 수신 권한을 사용자가 승인하지 않음..", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(this,"SMS 수신 권한을 부여받지 못함.",Toast.LENGTH_LONG).show();
                }
        }
    }
