using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cw3.Middlewares
{
    public class LoggingMiddleware
    {
        private readonly RequestDelegate _next;
        public LoggingMiddleware(RequestDelegate next)
        {
            _next = next;
        }
        public async Task InvokeAsync(HttpContext httpContext)
        {
            using (StreamWriter streamWriter = File.AppendText("Middlewares\\log.txt"))
            {
                var bodyStream = new StringBuilder();
                using (var reader = new StreamReader(httpContext.Request.Body, Encoding.UTF8, true, 1024, true))
                {
                    var body=await reader.ReadToEndAsync();
                    var lines = body.Split('\n');
                    foreach (var line in lines)
                    {
                        bodyStream.Append("\t\t" + line+"\n");
                    }
                }
                streamWriter.Write($"New request: {DateTime.Now.ToLongDateString()}\t{DateTime.Now.ToLongTimeString()}\n");
                streamWriter.Write($"\tMethod: {httpContext.Request.Method}\n\tRoute: {httpContext.Request.Path}\n\tBody:\n{bodyStream}");
                streamWriter.Write($"\tQueryString:\n\t\t{httpContext.Request.QueryString.ToString()}\n");
            }
            await _next(httpContext);
        }

    }
}
