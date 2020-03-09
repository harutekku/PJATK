using System;
using System.Net.Http;
using System.Text.RegularExpressions;

namespace Cw1
{
    public class Program
    {
        static async System.Threading.Tasks.Task Main(string[] args)
        {
            //Console.WriteLine("Hello World!");
            //int? tmp = null;
            //double tmp2 = 2;
            //string tmp3 = "Aaaa";
            //bool tmp4 = true;
            //var tmp5 = "maslo";
            //var newPerson = new Person { FirstName = "Janek" };

            var url = args.Length>0?args[0]:"http://pja.edu.pl";
            var httpClient = new HttpClient();
            var response = await httpClient.GetAsync(url);
            if (response.IsSuccessStatusCode)
            {
                var htmlContent = await response.Content.ReadAsStringAsync();
                var regex = new Regex("[a-z]+[a-z0-9]*@[a-z0-9]+\\.[a-z]+", RegexOptions.IgnoreCase);
                var matches = regex.Matches(htmlContent);
                foreach (var match in matches)
                {
                    Console.WriteLine(match.ToString());
                }
            }
        }
    }
}
