namespace cw2
{
    public class Student
    {
        public string Imie { get; set; }
        public string Nazwisko { get; set; }
        public string Kierunek { get; set; }
        public string Tryb { get; set; }
        public string Eska { get; set; }
        public System.DateTime? DataUr { get; set; }
        public string Mail { get; set; }
        public string ImieMatki { get; set; }
        public string ImieOjca { get; set; }
        public bool poprawnosc()
        {
            return Imie.Length > 0 & Nazwisko.Length > 0 & Kierunek.Length > 0 & Tryb.Length > 0 & Eska.Length > 0 & DataUr.HasValue & Mail.Length > 0 & ImieMatki.Length > 0 & ImieOjca.Length > 0;
        }
        public string get()
        {
            return "getuje";
        }
        public override string ToString()
        {
            return Imie + " " + Nazwisko + " " + Eska;
        }
        /*public override int GetHashCode()
        {
            return System.StringComparer.CurrentCultureIgnoreCase.GetHashCode($"{Imie}{Nazwisko}{Eska}");
        }*/
    }
}