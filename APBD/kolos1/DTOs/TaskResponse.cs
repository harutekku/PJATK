using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos1.DTOs
{
    public class TaskResponse
    {
        public int id { get; set; }
        public string ProjectName { get; set; }
        public DateTime ProjectDeadline { get; set; }
        public List<TaskList> List { get; set; }
        
    }
}
