package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.tugva.basaksehir.tugvabasaksehir.R;

public class HakkimizdaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkimizda);
        TextView txtHakkimizda = (TextView)findViewById(R.id.txtHakkimizda);
        ImageView imgFoto = (ImageView)findViewById(R.id.imgFoto);
        Picasso.with(getApplicationContext()).load("http://www.tugva.org/wp-content/uploads/2014/06/tugva-hakkimizda-1140x500.jpg").into(imgFoto);

        txtHakkimizda.setText("HAKKIMIZDA\n" +
                "Türkiye Gençlik Vakfı, birkaç gencin inisiyatifiyle kurulmuş, merkezi Türkiye, çalışma alanı dünya olan, yenilikçi olmaktan ve icat çıkarmaktan çekinmeyen “yeni nesil gençlik vakfı”dır. TÜGVA, geleneklerine bağlı kalarak, çağın gereklerini iyi okuyabilen, topluma ve insanlığa değer katan, saygı, paylaşım ve hassasiyet ilkelerini benimsemiş, öz güveni yüksek, yenilikçi, çalışkan, iyi ahlaklı, hoşgörülü, başarılı ve sorumluluk sahibi bir gençlik için çalışmaktadır.\n" +
                "\n" +
                "Yeni Nesil Vakıfçılık Tanımımız\n" +
                "\n" +
                "Yeni nesil gencin enerjisi, hızı ve dinamizmi ile iyilik odaklı işler yapan, deneyimini paylaşan ve yaşatan anlayışa sahip vakıfçılık modelidir.\n" +
                "\n" +
                "Misyonumuz\n" +
                "\n" +
                "Gençliğin sosyal, fiziksel, zihinsel, ruhsal ve manevi gelişimlerini önemseyen; bu yaklaşımlarla düşünen, tasarlayan ve hayatlarına temas eden bir misyon ile kendini devamlı geliştiren, yenilikçi ve nitelikli insan oluşumuna katkı sağlamak.\n" +
                "\n" +
                "Vizyonumuz\n" +
                "\n" +
                "Gelecekte kendimizi; yeni nesil vakıfçılık deneyimini tüm gençliğe yaşatmış ve onların ülkemize faydalı birer önemli şahsiyet olmasında en büyük katkıyı yapmış bir vakıf olarak görüyoruz.");
    }
}
