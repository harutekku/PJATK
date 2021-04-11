using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Text.Json;

namespace mp1
{
    public class Client : Person
    {
        static decimal discount = new decimal(0.1);
        public List<string> address { get; set; } //first address is default
        public byte? age { get; set; }

        public static List<Client> Extension { get; set; } = new List<Client>();

        public Client(string name, string surname, string address, byte? age = null)
        {
            this.name = name;
            this.surname = surname;
            this.address = new List<string>();
            this.address.Add(address);
            this.age = age;
            Extension.Add(this);
        }
        public Client()
        {
            Extension.Add(this);
        }
        public void addAddress(string address)
        {
            this.address.Add(address);
        }
        public static void SaveClients()
        {
            string jsonString = JsonSerializer.Serialize(Client.Extension);
            File.WriteAllText("clients.json", jsonString);
        }
        public static void RestoreClients()
        {
            string jsonString = File.ReadAllText("clients.json");
            Client.Extension = JsonSerializer.Deserialize<List<Client>>(jsonString);
        }
        public override string ToString()
        {
            return name + " " + surname + " " + age + " " + string.Join(", ",address);
        }
        public static void showClients()
        {
            foreach (var client in Extension)
            {
                Console.WriteLine(client);
            }
        }
    }
}
