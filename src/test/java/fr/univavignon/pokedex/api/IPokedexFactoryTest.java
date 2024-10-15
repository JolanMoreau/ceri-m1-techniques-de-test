package fr.univavignon.pokedex.api;
import  org.mockito.Mockito;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
public class IPokedexFactoryTest {
    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    @Before
    public void setUp() {
        // Création des mocks pour IPokemonMetadataProvider et IPokemonFactory
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        IPokedex pokedex = Mockito.mock(IPokedex.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        Mockito.when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory))
                .thenReturn(pokedex);
    }

    @Test
    public void testCreatePokedex() {
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertNotNull(createdPokedex);

    }
}
