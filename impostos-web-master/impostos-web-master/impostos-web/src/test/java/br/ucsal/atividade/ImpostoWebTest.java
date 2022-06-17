package br.ucsal.atividade;
	import org.jbehave.core.Embeddable;
	import org.jbehave.core.configuration.Configuration;
	import org.jbehave.core.configuration.Keywords;
	import org.jbehave.core.configuration.MostUsefulConfiguration;
	import org.jbehave.core.embedder.EmbedderControls;
	import org.jbehave.core.i18n.LocalizedKeywords;
	import org.jbehave.core.io.CodeLocations;
	import org.jbehave.core.io.LoadFromClasspath;
	import org.jbehave.core.io.StoryFinder;
	import org.jbehave.core.junit.JUnitStories;
	import org.jbehave.core.model.ExamplesTableFactory;
	import org.jbehave.core.model.TableTransformers;
	import org.jbehave.core.parsers.RegexStoryParser;
	import org.jbehave.core.reporters.Format;
	import org.jbehave.core.reporters.StoryReporterBuilder;
	import org.jbehave.core.steps.InjectableStepsFactory;
	import org.jbehave.core.steps.InstanceStepsFactory;
	import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
	import org.jbehave.core.steps.StepFinder;

	import java.util.Arrays;
	import java.util.List;
	import java.util.Locale;


	 public class ImpostoWebTest extends JUnitStories {

	     public ImpostoWebTest() {
	        EmbedderControls embedderControls = configuredEmbedder().embedderControls();
	        embedderControls.useStoryTimeouts("600");
	    }

	    @Override
	    public Configuration configuration() {
	        Class<? extends Embeddable> embeddableClass = this.getClass();
	        TableTransformers tableTransformers = new TableTransformers();

	        Keywords keywords = new LocalizedKeywords(new Locale("pt"));
	        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(keywords,
	                                                    new LoadFromClasspath(embeddableClass),
	                                                    tableTransformers);

	        return new MostUsefulConfiguration()
	                .useKeywords(keywords)
	                .useStepCollector(new MarkUnmatchedStepsAsPending(new StepFinder(), keywords))
	                .useStoryParser(new RegexStoryParser(examplesTableFactory))

	                .useStoryReporterBuilder(
	                        new StoryReporterBuilder().withDefaultFormats().
	                                withFormats(Format.CONSOLE, Format.TXT, Format.HTML));
	    }

	    @Override
	    public InjectableStepsFactory stepsFactory() {
	        return new InstanceStepsFactory(configuration(), new IrSteps());
	    }

	    @Override
	    public List<String> storyPaths() {
	        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()),
	                Arrays.asList("**/*.story"), Arrays.asList(""));
	    }
	}