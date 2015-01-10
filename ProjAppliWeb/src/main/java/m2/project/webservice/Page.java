package m2.project.webservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {

    private String name;
    private String about;
    private String phone;
    private String website;
    private String were_here_count;
    
    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

	public String getWere_here_count() {
		return were_here_count;
	}

	public void setWere_here_count(String were_here_count) {
		this.were_here_count = were_here_count;
	}
    
}