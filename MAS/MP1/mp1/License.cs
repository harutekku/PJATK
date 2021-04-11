﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace mp1
{
    public class License
    {
        public enum Licenses
        {
            Basic,   
            Forklift,
            Deliveries_accepting
        }

        DateTime LicenseDate;
        public Licenses LicenceType;
        
        public License()
        {
            this.LicenseDate = DateTime.Now;
            this.LicenceType = Licenses.Basic;
        }
        public License(Licenses licenses)
        {
            this.LicenseDate = DateTime.Now;
            this.LicenceType = licenses;
        }
    }
}
