Specification Heading
=====================
Test case 1: Positive LogIn test
Open page
Type username student into Username field
Type password Password123 into Password field
Push Submit button
Verify new page URL contains practicetestautomation.com/logged-in-successfully/
Verify new page contains expected text ('Congratulations' or 'successfully logged in')
Verify button Log out is displayed on the new page

    |userName       |  password | yeniHesapAdi   | ilkHesapAdı |
    |metekaan.sahin |Mete12345* |  Wete12345Yeni | Wete123 |
    |metekaan.sahin |Mete12345* |  WeteTestinium | Wete123 |
    |metekaan.sahin |Mete12345* |  Wete12133()?\ | Wete123 |
    |metekaan.sahin |Mete12345* |                | Wete123 |


Hesap İsmi Düzenleme
----------------
*Test anasayfasına git
*Anasayfa kontrollerini yap
*Login ol <userName>  <password>
*Logini doğrula ve transfer uygulamasını başlat
*Hesap sayfasını kontrol et
*ilk hesap adını kaydet
*Edit Account a git
*Edit Account ekranını kontrol et
*Yeni hesap adı tanımla <yeniHesapAdi>
*Hesap sayfasını kontrol et
*Hesap adının değiştirildiğini kontrol et <yeniHesapAdi>
*Edit Account a git
*Edit Account ekranını kontrol et
*ilk hesap adını tanımla <ilkHesapAdı>
* Hesabı kapat



