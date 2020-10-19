using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;
using System.Xml.Serialization;

namespace cw2
{
    [Serializable]
    public class Box
    {
        [XmlAttribute]
        [JsonProperty(Order = 1)]
        public string createdAt { get; set; }
        [XmlAttribute]
        [JsonProperty(Order = 2)]
        public string author { get; set; }
        [JsonProperty(Order = 3)]
        public HashSet<Student> studenci;
        [JsonProperty(Order = 4)]
        public List<NumberOfStudents> activeStudents;
    }
}
