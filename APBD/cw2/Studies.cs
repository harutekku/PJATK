using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;
using System.Xml.Serialization;

namespace cw2
{
    public class Studies
    {
        [XmlElement(ElementName = "name")]
        [JsonProperty("name")]
        public string faculty { get; set; }
        [XmlElement(ElementName = "mode")]
        [JsonProperty("mode")]
        public string mode { get; set; }
    }
}
