using kolos1.DTOs;
using System.Collections.Generic;

namespace kolos1.Services
{
    public interface IDbService
    {
        TaskResponse getTasks(string id);
        void addTask(TaskRequest taskRequest);
    }
}