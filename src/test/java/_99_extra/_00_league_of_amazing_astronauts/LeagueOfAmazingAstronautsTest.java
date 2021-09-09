package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();
    
    @Mock
    Astronaut a;
    @Mock
    
    Rocketship r;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
    
        //when
    	when(a.isTrained()).thenReturn(true);
    	underTest.prepareAstronaut(a);
    	
        //then
    	verify(a,times(1)).train();
    }

    @Test
    void itShouldLaunchRocket() {
        //given
    	String destination = "Mars";
        underTest.setRocketship(r);
        
        //when
        when(r.isLoaded()).thenReturn(true);
    	underTest.launchRocket(destination);
    	
        //then
    	verify(underTest.rocketship,times(1)).launch();
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
    	 //given
    	String destination = "mars";
        underTest.setRocketship(r);
        
        //when
        when(r.isLoaded()).thenReturn(true);
        Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
        
        //then
    	assertEquals(exceptionThrown.getMessage(),"Destination is unavailable");
    }

    @Test
    void itShouldThrowNotLoaded() {
        //given
    	String destination = "Mars";
        underTest.setRocketship(r);
        
        //when
        when(r.isLoaded()).thenReturn(false);
        Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
        
        //then
    	assertEquals(exceptionThrown.getMessage(),"Rocketship is not loaded");
    }
}