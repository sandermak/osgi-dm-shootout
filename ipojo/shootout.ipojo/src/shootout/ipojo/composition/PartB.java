package shootout.ipojo.composition;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.service.log.LogService;

import shootout.ipojo.composition.api.Provider;

@Component
@Provides
public class PartB implements Provider {

	@Requires
	private volatile LogService logService;

	@Validate
	void validate() {
		System.out.println("validate PartB. Got logService: " + logService);
	}
	
	@Invalidate
	void invalidate() {
		System.out.println("invalidate PartB.");
	}
}
