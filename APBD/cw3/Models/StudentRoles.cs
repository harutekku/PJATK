using System;
using System.Collections.Generic;

namespace cw3.Models
{
    public partial class StudentRoles
    {
        public string IndexNumber { get; set; }
        public int IdRole { get; set; }

        public virtual Roles IdRoleNavigation { get; set; }
        public virtual Student IndexNumberNavigation { get; set; }
    }
}
