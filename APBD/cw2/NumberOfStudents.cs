using Newtonsoft.Json;
using System.Xml.Serialization;

namespace cw2
{
    [XmlType("studies")]
    public class NumberOfStudents
    {
        [XmlAttribute]
        [JsonProperty("name")]
        public string name;
        [XmlAttribute]
        [JsonProperty("numberOfStudents")]
        public int numberOfStudents;
    }
}