using System.IO;
using System.Text;

namespace MPP3
{
    public class Lang
    {
        public string lang { get; set; }
        public LangSample[] langSamples { get; set; }
        public Lang(string lang)
        {
            this.lang = lang.Split('\\')[^1];
            string[] samples = Directory.GetFiles(lang, "*.txt");
            langSamples = new LangSample[samples.Length];
            for (int i = 0; i < samples.Length; i++) langSamples[i] = new LangSample(File.ReadAllText(samples[i], Encoding.ASCII), this.lang);
        }
    }
}
