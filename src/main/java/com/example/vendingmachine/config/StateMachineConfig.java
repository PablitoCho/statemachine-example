package com.example.vendingmachine.config;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
            states
                .withStates()
                .initial(States.S0)
                .state(States.S5)
                .state(States.S10)
                .state(States.S15)
                .state(States.S20)
                .state(States.S25);
//                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source(States.S0)
                    .target(States.S10)
                    .event(Events.InsertNickel)
                .and()
                .withExternal()
                    .source(States.S10)
                    .target(States.S15)
                    .event(Events.InsertNickel);
    }


}
