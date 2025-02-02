🚀 Kurulum

1️⃣ Gereksinimler

Aşağıdaki bileşenlerin sisteminizde kurulu olması gerekmektedir:

Java 11+

Apache Maven

Gauge (https://docs.gauge.org/getting_started/installing-gauge.html)

Google Chrome & ChromeDriver

2️⃣ Projeyi Klonlayın

git clone
cd para-transfer-otomasyon

3️⃣ Bağımlılıkları Yükleyin

mvn clean install

🏃 Testleri Çalıştırma

Tüm Testleri Çalıştırma

gauge run specs

Belirli Bir Testi Çalıştırma

gauge run specs/T.C.1.spec

Test Raporlarını Görüntüleme

Testler tamamlandıktan sonra reports/html-report/index.html dosyasından raporları görüntüleyebilirsiniz.

📑 Test Kapsamı

Test edilecek fonksiyonlar:

✅ Uygulama giriş ekranı açılmalı

✅ Hesap ismi düzenlenebilir olmalı (boş bırakılamaz, sadece sayılar olamaz)

✅ Hesaplar arasında para transferi yapılabilmeli

✅ Kredi kartı ile para yatırma işlemi gerçekleştirilmeli

✅ Bakiye negatif olmamalı

⚠️ Riskler ve Olası Problemler

🔴 Kullanıcı giriş yaptıktan sonra ana ekrana ulaşamazsa test başarısız olur.

🔴 Para transferi başarısız olursa, işlem tutarı hesap bakiyesine yansımayabilir.

🔴 Kredi kartı doğrulama hataları testleri başarısız kılabilir.

🛠 Kullanılan Teknolojiler

Java 11

Gauge (Test Otomasyon Çerçevesi)

Selenium 4 (Tarayıcı Otomasyonu)

Maven (Bağımlılık Yönetimi)

WebDriverManager (ChromeDriver Yönetimi)

📌 Yazar

Mete Kaan ŞAHİN