package com.tensquare.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 * @author Administrator
 *
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

//	//服务器公钥
//	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Dw9nwjBmDD/Ca1QnRGy"
//											 + "GjtLbF4CX2EGGS7iqwPToV2UUtTDDemq69P8E+WJ4n5W7Iln3pgK+32y19B4oT5q"
//											 + "iUwXbbEaAXPPZFmT5svPH6XxiQgsiaeZtwQjY61qDga6UH2mYGp0GbrP3i9TjPNt"
//											 + "IeSwUSaH2YZfwNgFWqj+y/0jjl8DUsN2tIFVSNpNTZNQ/VX4Dln0Z5DBPK1mSskd"
//											 + "N6uPUj9Ga/IKnwUIv+wL1VWjLNlUjcEHsUE+YE2FN03VnWDJ/VHs7UdHha4d/nUH"
//											 + "rZrJsKkauqnwJsYbijQU+a0HubwXB7BYMlKovikwNpdMS3+lBzjS5KIu6mRv1GoE"
//											 + "vQIDAQAB";
//
//	//服务器私钥(经过pkcs8格式处理)
//	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoPD2fCMGYMP8J"
//				 								 + "rVCdEbIaO0tsXgJfYQYZLuKrA9OhXZRS1MMN6arr0/wT5YniflbsiWfemAr7fbLX"
//				 								 + "0HihPmqJTBdtsRoBc89kWZPmy88fpfGJCCyJp5m3BCNjrWoOBrpQfaZganQZus/e"
//				 								 + "L1OM820h5LBRJofZhl/A2AVaqP7L/SOOXwNSw3a0gVVI2k1Nk1D9VfgOWfRnkME8"
//				 								 + "rWZKyR03q49SP0Zr8gqfBQi/7AvVVaMs2VSNwQexQT5gTYU3TdWdYMn9UeztR0eF"
//				 								 + "rh3+dQetmsmwqRq6qfAmxhuKNBT5rQe5vBcHsFgyUqi+KTA2l0xLf6UHONLkoi7q"
//				 								 + "ZG/UagS9AgMBAAECggEBANP72QvIBF8Vqld8+q7FLlu/cDN1BJlniReHsqQEFDOh"
//				 								 + "pfiN+ZZDix9FGz5WMiyqwlGbg1KuWqgBrzRMOTCGNt0oteIM3P4iZlblZZoww9nR"
//				 								 + "sc4xxeXJNQjYIC2mZ75x6bP7Xdl4ko3B9miLrqpksWNUypTopOysOc9f4FNHG326"
//				 								 + "0EMazVaXRCAIapTlcUpcwuRB1HT4N6iKL5Mzk3bzafLxfxbGCgTYiRQNeRyhXOnD"
//				 								 + "eJox64b5QkFjKn2G66B5RFZIQ+V+rOGsQElAMbW95jl0VoxUs6p5aNEe6jTgRzAT"
//				 								 + "kqM2v8As0GWi6yogQlsnR0WBn1ztggXTghQs2iDZ0YkCgYEA/LzC5Q8T15K2bM/N"
//				 								 + "K3ghIDBclB++Lw/xK1eONTXN+pBBqVQETtF3wxy6PiLV6PpJT/JIP27Q9VbtM9UF"
//				 								 + "3lepW6Z03VLqEVZo0fdVVyp8oHqv3I8Vo4JFPBDVxFiezygca/drtGMoce0wLWqu"
//				 								 + "bXlUmQlj+PTbXJMz4VTXuPl1cesCgYEA6zu5k1DsfPolcr3y7K9XpzkwBrT/L7LE"
//				 								 + "EiUGYIvgAkiIta2NDO/BIPdsq6OfkMdycAwkWFiGrJ7/VgU+hffIZwjZesr4HQuC"
//				 								 + "0APsqtUrk2yx+f33ZbrS39gcm/STDkVepeo1dsk2DMp7iCaxttYtMuqz3BNEwfRS"
//				 								 + "kIyKujP5kfcCgYEA1N2vUPm3/pNFLrR+26PcUp4o+2EY785/k7+0uMBOckFZ7GIl"
//				 								 + "FrV6J01k17zDaeyUHs+zZinRuTGzqzo6LSCsNdMnDtos5tleg6nLqRTRzuBGin/A"
//				 								 + "++xWn9aWFT+G0ne4KH9FqbLyd7IMJ9R4gR/1zseH+kFRGNGqmpi48MS61G0CgYBc"
//				 								 + "PBniwotH4cmHOSWkWohTAGBtcNDSghTRTIU4m//kxU4ddoRk+ylN5NZOYqTxXtLn"
//				 								 + "Tkt9/JAp5VoW/41peCOzCsxDkoxAzz+mkrNctKMWdjs+268Cy4Nd09475GU45khb"
//				 								 + "Y/88qV6xGz/evdVW7JniahbGByQhrMwm84R9yF1mNwKBgCIJZOFp9xV2997IY83S"
//				 								 + "habB/YSFbfZyojV+VFBRl4uc6OCpXdtSYzmsaRcMjN6Ikn7Mb9zgRHR8mPmtbVfj"
//				 								 + "B8W6V1H2KOPfn/LAM7Z0qw0MW4jimBhfhn4HY30AQ6GeImb2OqOuh3RBbeuuD+7m"
//				 								 + "LpFZC9zGggf9RK3PfqKeq30q";

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu1nRFdfSQpSr+gkPBN6Z" +
			"hKxUdx11oMY35Y1mZ7Xoa6vi5yCHkEwDGlomptxqkbjv1J/2JApePOdZLe9GXo4k" +
			"cuIs5mofpRaqRWodkXLYK38Ps/kXTC7gZiEa8ttAWYsckSW/uhebqyUapigQW8QE" +
			"QcVapHTN6NBbllRtjcfqavbVvXotoIcgUOtWgB2BX4L6e2HeKieynoCd520+a5M6" +
			"bFsXfCj275me1dd532X212QPoDAF7dNNFCCw6gtLAj/jdN6oxGasp746PZqGfRUt" +
			"FSEFcyTEA8tej6p3ytNzs40AuuBVRstyK2H77fa2Komeacp26YIXWGaypEOEoH5H" +
			"2wIDAQAB";

	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7WdEV19JClKv6" +
			"CQ8E3pmErFR3HXWgxjfljWZntehrq+LnIIeQTAMaWiam3GqRuO/Un/YkCl4851kt" +
			"70ZejiRy4izmah+lFqpFah2Rctgrfw+z+RdMLuBmIRry20BZixyRJb+6F5urJRqm" +
			"KBBbxARBxVqkdM3o0FuWVG2Nx+pq9tW9ei2ghyBQ61aAHYFfgvp7Yd4qJ7KegJ3n" +
			"bT5rkzpsWxd8KPbvmZ7V13nfZfbXZA+gMAXt000UILDqC0sCP+N03qjEZqynvjo9" +
			"moZ9FS0VIQVzJMQDy16PqnfK03OzjQC64FVGy3IrYfvt9rYqiZ5pynbpghdYZrKk" +
			"Q4SgfkfbAgMBAAECggEAAjxKIGaY3a6EdtFQ2pAFaO3dfCZf1vFGjXiodihXg6Fj" +
			"bbqXo4MkrlXRDECpDOAWDIV0HG37PeLMz52BS3OKv8wvGPi/M+sXUQi5HZkvNyWm" +
			"/FDGlGQizVUryyYOmILublTy+rd5ZgtlTAJCX7XxDw/ZywiRxGtoDF0piX2PMIOI" +
			"QqEg8qrnv7y9tHnznfPoPTvtyksgonNYFOie1M5HHouNA+WjPgBFEUoUn8sILJJl" +
			"CXKlWLpTRHRlld1kYOSNPy8WSWQzm4wEcyDrrTkmJUmFOMk0wXZK49jbi16esNlp" +
			"sjhncsrUxXosW/k/CJgYUgGHr7suNL2NAaiusTsfcQKBgQDin9MQKqwjyZ/1Mm/k" +
			"muey4/uMa68I9rXNJZ7Bm5w9HXKtO7GAxnOvpXsHxya4lB+xjKxtu6MB0wDWhheW" +
			"+ASF74mafEgfMOblZ+kL73yZUoiykgiyPmq3hTWq5D/gw6Pvk0l7VpjzOwfoVALu" +
			"PKefnMIEwgSbiOsstMV6V2PIJwKBgQDTosNoKpv+7+Qdd7GCnCtB+t0lJkXx0GSA" +
			"zA1tSWG8OG14LpM9+NuZkDsVKgmQ4311Cxs7beBrFLZtno7LhFy1/wDEy49+fNtC" +
			"yCZpe6Xg4CrRJGgrFjiZPurZ68Kx4iCKZmkg6Y70sIids52ucmx/v/zC7ZcJ6cBa" +
			"U8Si3cm/LQKBgGrrjODrwRuBXP6KNVBd5UL5XQz7ITHRdCS9wxEFIcmTwy8IIs7m" +
			"0l3kbQ9WXmZnPCzAefmrtVR/AL41tZt8mGW4hetlB4HvyIAjbkg7TUXpMqVNUN0H" +
			"pcHAD3n5Bko9A3NcwiZ2zOK0zx5FxLwx0cP0aXVlNFdNWedVwnXeFS2xAoGAEUoY" +
			"wKfKerdKYYXnwI3VktxVuLU5vZmSvFrvNeBq1Z+egdT8PnHYLuFzkjUO9wE+vJMd" +
			"tGszjEvy412yuLmlgVDF1U3z+RdmKjyDttZdJNPra46X2/1CvnI/aXIzQ6j/k47u" +
			"Q7wQ67iIUKnd1eSPLJiYo6wZTLwF9NsnE08fLjkCgYEAnelZcq9eMjqBw64eZrcc" +
			"pjB/3aOAiGBbylMfGjLmrW3WApJpoaW2P4XCskTvW5PlJOeUPg3nV04N95FbFnot" +
			"1QxJnA7p4NHymass3YFpIYwNmpU7qQulDHhM72ajPgNsihkdUckL6tyzPOTR3x5e" +
			"JnZ8Yq8gkL+i8lNKZkCPJ8g=";

	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
