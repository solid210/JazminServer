/**
 * 
 */
package jazmin.test.server.rpc;

import jazmin.core.Jazmin;
import jazmin.driver.rpc.JazminRpcDriver;
import jazmin.log.LoggerFactory;
import jazmin.util.RandomUtil;


/**
 * @author yama
 * 23 Dec, 2014
 */
public class RPCClientTest {
	//
	public static void main(String[] args) throws Exception{
		test1();
		//test2();
	}
	private static void test2(){
		LoggerFactory.setLevel("INFO");
		JazminRpcDriver driver=new JazminRpcDriver();
		driver.addRemoteServer("1","1", "localhost", 6001);
		Jazmin.addDriver(driver);
		Jazmin.start();
		TestRemoteService service=driver.create(TestRemoteService.class,"1");
		service.methodA("1");
	}
	//
	public static void test1(){
		LoggerFactory.setLevel("INFO");
		JazminRpcDriver driver=new JazminRpcDriver();
		driver.addRemoteServer("1","1", "localhost", 6001);
		Jazmin.addDriver(driver);
		Jazmin.start();
		TestRemoteService service=driver.create(TestRemoteService.class,"1");
		for(int j=0;j<500;j++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					long time=RandomUtil.randomInt(5)*2*1000;
					while(true){
						try{
							service.timeoutMethod(time);
						}catch(Exception e){
							time=1;
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		}
	}
}
