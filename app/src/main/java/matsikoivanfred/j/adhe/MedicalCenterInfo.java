package matsikoivanfred.j.adhe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class MedicalCenterInfo extends Activity {
    TextView name;
    TextView mobile;
    TextView email;
    TextView address;
    Button updateButton;
    DBHelper dbHelper;
    Integer centerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_center_info);
        name=(TextView)findViewById(R.id.tvMedicalCenterName);
        mobile=(TextView)findViewById(R.id.tvMedicalCenterMobile);
        email=(TextView)findViewById(R.id.tvMedicalCenterEmail);
        address=(TextView)findViewById(R.id.tvMedicalCenterAddress);
        updateButton=(Button)findViewById(R.id.btMedicalCenterUpdate);
        Intent intent=getIntent();
        centerId=intent.getIntExtra("center_id",0);
        dbHelper = new DBHelper(getApplicationContext());
        HashMap<String, String> map = new HashMap<String, String>();
        map= dbHelper.getAboutOneMedicalCenter(centerId);
        name.setText(map.get("name"));
        mobile.setText(map.get("mobile"));
        email.setText(map.get("email"));
        address.setText(map.get("address"));
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateHealthCareIntent = new Intent(getApplicationContext(), UpdateHealthCare.class);
                updateHealthCareIntent.putExtra("center_id",centerId);
                startActivity(updateHealthCareIntent);
                finish();

            }
        });
    }
    public void centerCall(View view){
        Uri call = Uri.parse("tel:" + mobile.getText().toString());
        Intent dcal = new Intent(Intent.ACTION_CALL, call);
        startActivity(dcal);
    }
}
