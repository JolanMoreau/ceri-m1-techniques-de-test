package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Test class for IPokemonTrainerFactory.
 */
public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;
    private IPokedex pokedex;

    @Before
    public void setUp() {
        pokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokedex = Mockito.mock(IPokedex.class);
        PokemonTrainer ashTrainer = new PokemonTrainer("Ash", Team.VALOR, pokedex);
        PokemonTrainer mistyTrainer = new PokemonTrainer("Misty", Team.MYSTIC, pokedex);
        Mockito.when(pokedexFactory.createPokedex(Mockito.any(), Mockito.any())).thenReturn(pokedex);
        Mockito.when(pokemonTrainerFactory.createTrainer("Ash", Team.VALOR, pokedexFactory)).thenReturn(ashTrainer);
        Mockito.when(pokemonTrainerFactory.createTrainer("Misty", Team.MYSTIC, pokedexFactory)).thenReturn(mistyTrainer);
    }

    @Test
    public void testCreateTrainerAsh() {
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Ash", Team.VALOR, pokedexFactory);
        assertNotNull(trainer);
        assertEquals("Ash", trainer.getName());
        assertEquals(Team.VALOR, trainer.getTeam());
        assertEquals(pokedex, trainer.getPokedex());
    }

    @Test
    public void testCreateTrainerMisty() {
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Misty", Team.MYSTIC, pokedexFactory);
        assertNotNull(trainer);
        assertEquals("Misty", trainer.getName());
        assertEquals(Team.MYSTIC, trainer.getTeam());
        assertEquals(pokedex, trainer.getPokedex());
    }
}
