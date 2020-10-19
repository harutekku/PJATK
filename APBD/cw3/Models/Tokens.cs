using System;
using System.Collections.Generic;

namespace cw3.Models
{
    public partial class Tokens
    {
        public int IdToken { get; set; }
        public string TokenValue { get; set; }
        public string IndexNumber { get; set; }

        public virtual Student IndexNumberNavigation { get; set; }
    }
}
