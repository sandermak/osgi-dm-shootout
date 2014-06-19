package shootout.ipojo.composition;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.service.log.LogService;

@Component
public class PartA { // implements Provider
	
	@Requires
	private volatile LogService logService;

	@Validate
	void validate() {
		System.out.println("validate PartA. Got LogService: " + logService);
	}
	
	@Invalidate
	void invalidate() {
		System.out.println("invalidate PartA.");
	}
}
