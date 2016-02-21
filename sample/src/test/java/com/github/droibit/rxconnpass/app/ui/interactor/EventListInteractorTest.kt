package com.github.droibit.rxconnpass.app.ui.interactor

import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import com.github.droibit.rxconnpass.app.util.RxSchedulersOverrideRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import rx.Observable
import rx.subscriptions.CompositeSubscription

class EventListInteractorTest {

    @Rule
    @JvmField
    val mockito = MockitoJUnit.rule()
    @Rule
    @JvmField
    val overrideSchedulers = RxSchedulersOverrideRule()

    @Mock
    lateinit var searchAction: SearchAction
    @Mock
    lateinit var view: EventListView
    @Mock
    lateinit var mockEvents: List<Event>

    lateinit var interactor: EventListInteractor

    @Before
    fun setup() {
        interactor = EventListInteractor(action = searchAction,
                                         compositeSubscription = CompositeSubscription())
        interactor.init(view)
    }

    @After
    fun tearDown() {
        interactor.onDetach()
    }

    @Test
    fun searchByKeyword() {
        doReturn(Observable.just(mockEvents))
                .`when`(searchAction)
                .search("kotlin")

        interactor.searchByKeyword("kotlin")
        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).showContent()
        verify(view, never()).showError()
    }
}