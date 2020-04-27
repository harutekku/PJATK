using kolos1.DTOs;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace kolos1.Services
{
    public class MssqlService : IDbService
    {
        public TaskResponse getTasks(string id)
        {
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            using (var command = new SqlCommand("select * from Project p left join Task t on p.idProject=t.IdProject left join TaskType tt on t.IdTaskType=tt.IdTaskType where p.IdProject=@id order by t.Deadline desc;", connection))
            {
                command.Parameters.AddWithValue("id", id);
                connection.Open();
                var dr = command.ExecuteReader();
                if (!dr.Read()) throw new Exception();
                var response = new TaskResponse();
                response.id = int.Parse(id);
                response.ProjectName = dr["p.Name"].ToString();
                response.ProjectDeadline = DateTime.Parse(dr["p.Name"].ToString());
                var list = new List<TaskList>();
                response.List = list;
                do
                {
                    var task = new TaskList();
                    task.IdTask = (int)dr["t.IdTask"];
                    task.Name = dr["t.Name"].ToString();
                    task.Description = dr["t.Description"].ToString();
                    task.Deadline = DateTime.Parse(dr["t.DeadLine"].ToString());
                    task.IdTaskType = (int)dr["t.IdTaskType"];
                    task.TaskTypeName = dr["tt.Name"].ToString();
                    task.IdAssignetTo = (int)dr["t.IdAssignetTo"];
                    task.IdCreator = (int)dr["t.IdCreator"];
                    list.Add(task);
                } while (dr.Read());
                dr.Close();
                return response;
            }
        }
        public void addTask(TaskRequest taskRequest)
        {
            string com = "select IdTaskType, Name from TaskType where name=@TaskTypeName";
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            {
                connection.Open();
                using (var tran = connection.BeginTransaction())
                using (var command = new SqlCommand(com, connection, tran))
                {
                    try
                    {
                        command.Parameters.AddWithValue("TaskTypeName", taskRequest.TaskType.Name);
                        command.Parameters.AddWithValue("TaskTypeId", taskRequest.TaskType.IdTaskType);
                        var dr = command.ExecuteReader();
                        if (!dr.Read())
                        {
                            dr.Close();
                            command.CommandText = "insert into TaskType(IdTaskType, Name) values (@TaskTypeId, @TaskName);";
                            command.ExecuteNonQuery();
                        }
                        dr.Close();

                        command.CommandText = "insert into Task (IdTask, Name, Description, Deadline, IdProject, IdTaskType, IdAssignedTo, IdCreator) " +
                            "values ((select MAX(IdTask)+1 from Task), @Name, @Desc, @Deadline, @IdProject, @IdTaskType, @IdAssign, @IdCreator)";
                        command.Parameters.AddWithValue("Name", taskRequest.Name);
                        command.Parameters.AddWithValue("Desc", taskRequest.Description);
                        command.Parameters.AddWithValue("Deadline", taskRequest.Deadline);
                        command.Parameters.AddWithValue("IdProject", taskRequest.IdProject);
                        command.Parameters.AddWithValue("IdTaskType", taskRequest.TaskType.IdTaskType);
                        command.Parameters.AddWithValue("IdAssign", taskRequest.IdAssignetTo);
                        command.Parameters.AddWithValue("IdCreator", taskRequest.IdCreator);
                        command.ExecuteNonQuery();

                        tran.Commit();
                    }
                    catch (SqlException)
                    {
                        tran.Rollback();
                        throw new Exception();
                    }

                }

            }
        }
    }
}
