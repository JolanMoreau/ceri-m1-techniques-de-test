package fr.univavignon.pokedex.api;
import  org.mockito.Mockito;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class IPokemonMetadataProviderTest {
    private IPokemonMetadataProvider metadataProvider;

    @Before
    public void setUp() throws PokedexException {
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);

        PokemonMetadata bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata aqualiMetadata = new PokemonMetadata(134, "Aquali", 186, 168, 260);

        Mockito.when(metadataProvider.getPokemonMetadata(0)).thenReturn(bulbizarreMetadata);
        Mockito.when(metadataProvider.getPokemonMetadata(134)).thenReturn(aqualiMetadata);

        Mockito.when(metadataProvider.getPokemonMetadata(999)).thenThrow(new PokedexException("Invalid index"));
    }

    @Test
    public void testGetPokemonMetadataBulbizarre() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
    }

    @Test
    public void testGetPokemonMetadataAquali() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(134);
        assertNotNull(metadata);
        assertEquals(134, metadata.getIndex());
        assertEquals("Aquali", metadata.getName());
        assertEquals(186, metadata.getAttack());
        assertEquals(168, metadata.getDefense());
        assertEquals(260, metadata.getStamina());
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        metadataProvider.getPokemonMetadata(999);
    }
}
