package com.sunteorum.novious.helper;

import com.squareup.otto.Bus;

/**
 * Created by KYO on 2015/8/12.
 */
public final class BusHelper {

	private static final Bus BUS = new Bus();

	public static Bus getInstance() {
		return BUS;
	}

	private BusHelper() {
		// No instances.
	}

	public class LocationChangedEvent {
		public final float lat;
		public final float lon;

		public LocationChangedEvent(float lat, float lon) {
			this.lat = lat;
			this.lon = lon;
		}
	}

	public class LocationClearEvent {
	}


}
