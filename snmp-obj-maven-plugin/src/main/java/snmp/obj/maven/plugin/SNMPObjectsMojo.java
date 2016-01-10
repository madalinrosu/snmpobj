package snmp.obj.maven.plugin;

import java.io.File;
import java.util.Arrays;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import snmp.obj.tools.MIBCompiler;


@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class SNMPObjectsMojo extends AbstractMojo {

    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
	@Parameter(defaultValue="${project.basedir}")
    private File projectDirectory;

	@Parameter
	private String[] args;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		  getLog().info( Arrays.asList(args).toString() );
		  
		  MIBCompiler.outputDir = projectDirectory.getPath() + 
				  File.separator + "src" +  
				  File.separator + "main" +  
				  File.separator + "java";
		  
		  //MIBCompiler.main(new String[] { "-i", "-std", "-fsrc/main/resources/mibs/RFC1213-MIB"} );
		  //MIBCompiler.main(new String[] { "-i", "-std", "-fsrc/main/resources/mibs/HOST-RESOURCES-MIB"} );
		  //MIBCompiler.main(new String[] { "-i", "-std", "-fsrc/main/resources/mibs/usm.mib"} );
		  //MIBCompiler.main(new String[] { "-c", "-ent", "-asn112394", "-fsrc/main/resources/mibs"} );
		  MIBCompiler.main(args);
	}

}
