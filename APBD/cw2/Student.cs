using Newtonsoft.Json;
using System;
using System.Xml.Serialization;

namespace cw2
{
    [Serializable]
    public class Student
    {
        [XmlElement(ElementName = "fname")]
        [JsonProperty("fname", Order = 2)]
        public string Imie { get; set; }
        [XmlElement(ElementName = "lname")]
        [JsonProperty("lname", Order = 3)]
        public string Nazwisko { get; set; }
        [XmlAttribute(AttributeName = "indexNumber")]
        [JsonProperty("indexNumber",Order = 1)]
        public string Eska { get; set; }
        [XmlElement(ElementName = "birthdate")]
        [JsonProperty("birthdate", Order = 4)]
        public string DataUr { get; set; }
        [XmlElement(ElementName = "email")]
        [JsonProperty("email", Order = 5)]
        public string Mail { get; set; }
        [XmlElement(ElementName = "mothersName")]
        [JsonProperty("mothersName", Order = 6)]
        public string ImieMatki { get; set; }
        [XmlElement(ElementName = "fathersName")]
        [JsonProperty("fathersName", Order = 7)]
        public string ImieOjca { get; set; }
        [JsonProperty(Order = 8)]
        public Studies Studies { get; set; }
        public bool poprawnosc()
        {
            return Imie.Length > 0 & Nazwisko.Length > 0 & Eska.Length > 0 & DataUr.Length > 0 & Mail.Length > 0 & ImieMatki.Length > 0 & ImieOjca.Length > 0;
        }
        public override string ToString() //do testowania
        {
            return Imie + " " + Nazwisko + " " + Eska;
        }
    }
}