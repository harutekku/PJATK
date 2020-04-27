using System;

namespace kolos1.DTOs
{
    public class TaskList
    {
        public int IdTask { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Deadline { get; set; }
        public int IdTaskType { get; set; }
        public string TaskTypeName { get; set; }
        public int IdAssignetTo { get; set; }
        public int IdCreator { get; set; }
    }
}