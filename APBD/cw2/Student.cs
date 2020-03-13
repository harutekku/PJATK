using Newtonsoft.Json;
using System;
using System.Xml.Serialization;

namespace cw2
{
    [Serializable]
    public class Student
    {
        [XmlElement(ElementName = "fname")]
        [JsonProperty("fname")]
        public string Imie { get; set; }
        [XmlElement(ElementName = "lname")]
        [JsonProperty("lname")]
        public string Nazwisko { get; set; }
        [XmlAttribute(AttributeName = "indexNumber")]
        [JsonProperty("indexNumber")]
        public string Eska { get; set; }
        [XmlElement(ElementName = "birthdate")]
        [JsonProperty("birthdate")]
        public string DataUr { get; set; }
        [XmlElement(ElementName = "email")]
        [JsonProperty("email")]
        public string Mail { get; set; }
        [XmlElement(ElementName = "mothersName")]
        [JsonProperty("mothersName")]
        public string ImieMatki { get; set; }
        [XmlElement(ElementName = "fathersName")]
        [JsonProperty("fathersName")]
        public string ImieOjca { get; set; }
        public Studies Studies { get; set; }
        public bool poprawnosc()
        {
            return Imie.Length > 0 & Nazwisko.Length > 0 & Eska.Length > 0 & DataUr.Length > 0 & Mail.Length > 0 & ImieMatki.Length > 0 & ImieOjca.Length > 0;
        }
        public string get()
        {
            return "getuje";
        }
        public override string ToString()
        {
            return Imie + " " + Nazwisko + " " + Eska;
        }
    }
}