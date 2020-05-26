package nl.hu.v1wac.firstapp.model;

public class ServiceProvider {
	private static WorldService worldService;

	static {
		try {
			worldService = new WorldService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WorldService getWorldService() {
		return worldService;
	}
}