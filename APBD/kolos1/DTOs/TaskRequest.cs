using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos1.DTOs
{
    public class TaskRequest
    {

        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Deadline { get; set; }
        public int IdProject { get; set; }
        public int IdAssignetTo { get; set; }
        public int IdCreator { get; set; }
        public TaskType TaskType { get; set; }
    }
}
