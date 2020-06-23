public class RabinKarpAlgorithm {
    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int detect_length = input.length()-pattern.length();
        if (detect_length<0){
            return -1;
        }
        StringBuilder b = new StringBuilder();
        String c = input;
        for(int i=0; i<pattern.length(); i++){
            b.append(c.charAt(i));
        }
        RollingString In = new RollingString(b.toString(), pattern.length());
        RollingString Pat = new RollingString(pattern, pattern.length());
        for(int i=0; i<detect_length; i++) {
            if(In.equals(Pat) ){
                if(In.hashCode()==Pat.hashCode()){
                    return i;
                }
            }
            In.addChar(c.charAt(pattern.length()+i));
        }
        return -1;
    }
}
/* wiki algorithm
function RabinKarp(string s[1..n], string pattern[1..m])
    hpattern := hash(pattern[1..m]);
    for i from 1 to n-m+1
        hs := hash(s[i..i+m-1])
        if hs = hpattern
            if s[i..i+m-1] = pattern[1..m]
                return i
    return not found
 */